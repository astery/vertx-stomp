package io.vertx.ext.stomp.impl;

import com.jayway.awaitility.Awaitility;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.stomp.*;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the STOMP client.
 */
@RunWith(VertxUnitRunner.class)
public class StompClientImplTest {

  private Vertx vertx;
  private StompServer server;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    server = Stomp.createStompServer(vertx)
        .handler(StompServerHandler.create(vertx))
        .listen(context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    server.close(context.asyncAssertSuccess());
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testConnection(TestContext context) {
    Async async = context.async();
    StompClient client = Stomp.createStompClient(vertx);
    client.connect(ar -> {
      if (ar.failed()) {
        context.fail("Connection failed");
        return;
      }
      context.assertNotNull(ar.result());
      context.assertNotNull(ar.result().session());
      context.assertNotNull(ar.result().server());
      context.assertNotNull(ar.result().version());
      async.complete();
    });
  }

  @Test
  public void testConnectionWithStompFrame(TestContext context) {
    Async async = context.async();
    StompClient client = Stomp.createStompClient(vertx, new StompClientOptions().setUseStompFrame(true));
    client.connect(ar -> {
      if (ar.failed()) {
        context.fail("Connection failed");
        return;
      }
      context.assertNotNull(ar.result());
      context.assertNotNull(ar.result().session());
      context.assertNotNull(ar.result().server());
      context.assertNotNull(ar.result().version());
      async.complete();
    });
  }

  @Test(timeout = 5000)
  public void testSendingMessages(TestContext context) {
    Async async = context.async();
    StompClient client = Stomp.createStompClient(vertx);
    client.connect(ar -> {
      if (ar.failed()) {
        context.fail("Connection failed");
        return;
      }
      ar.result().send("/hello", Buffer.buffer("this is my content"), frame -> {
        context.assertEquals(frame.getDestination(), "/hello");
        async.complete();
      });
    });
  }

  @Test
  public void testConnectionAndDisconnect(TestContext context) {
    Async async = context.async();
    StompClient client = Stomp.createStompClient(vertx, new StompClientOptions().setUseStompFrame(true));
    client.connect(ar -> {
      if (ar.failed()) {
        context.fail("Connection failed");
        return;
      }
      ar.result().disconnect(frame -> async.complete());
    });
  }

  @Test
  public void testClientHeartbeatWhenNoServerActivity(TestContext context) {
    AtomicReference<StompClientConnection> reference = new AtomicReference<>();
    server.close();
    AsyncLock<StompServer> lock = new AsyncLock<>();
    server = StompServer.create(vertx,
        new StompServerOptions().setHeartbeat(new JsonObject().put("x", 10).put("y", 10)))
        // Disable ping frame:
        .handler(StompServerHandler.create(vertx).pingHandler(v -> {
        }))
        .listen(lock.handler());
    lock.waitForSuccess();

    StompClient client = Stomp.createStompClient(vertx, new StompClientOptions().setHeartbeat(new JsonObject().put
        ("x", 10).put("y", 10)));
    client.connect(ar -> reference.set(ar.result()));

    // Wait until inactivity is detected.
    Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(
        () -> reference.get().session() == null
    );
  }

  @Test
  public void testClientHeartbeatWithServerActivity(TestContext context) throws InterruptedException {
    AtomicReference<StompClientConnection> reference = new AtomicReference<>();
    AsyncLock lock = new AsyncLock<>();
    server.close(lock.handler());
    lock.waitForSuccess();
    lock = new AsyncLock();
    server = StompServer.create(vertx,
        new StompServerOptions().setHeartbeat(new JsonObject().put("x", 10).put("y", 10)))
        .handler(StompServerHandler.create(vertx))
        .listen(lock.handler());
    lock.waitForSuccess();

    StompClient client = Stomp.createStompClient(vertx, new StompClientOptions().setHeartbeat(new JsonObject().put
        ("x", 10).put("y", 10)));
    client.connect(ar -> reference.set(ar.result()));

    Thread.sleep(1000);
    assertThat(reference.get().server()).isNotNull();
  }

  @Test
  public void testServerHeartbeatWhenNoClientActivity(TestContext context) {
    AtomicReference<StompClientConnection> reference = new AtomicReference<>();
    server.close();
    AsyncLock<StompServer> lock = new AsyncLock<>();
    server = StompServer.create(vertx,
        new StompServerOptions().setHeartbeat(new JsonObject().put("x", 10).put("y", 10)))
        .handler(StompServerHandler.create(vertx))
        .listen(lock.handler());
    lock.waitForSuccess();

    StompClient client = Stomp.createStompClient(vertx, new StompClientOptions().setHeartbeat(new JsonObject().put
        ("x", 10).put("y", 10)));
    client.connect(ar -> {
      reference.set(ar.result());
      // Disable ping frame:
      ar.result().pingHandler(connection -> {
      });
    });

    // Wait until inactivity is detected.
    Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(
        () -> reference.get().session() == null
    );
  }
}