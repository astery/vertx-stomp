/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.ext.stomp;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.core.http.ServerWebSocket
import io.vertx.groovy.core.net.NetServer
import io.vertx.groovy.core.Vertx
import io.vertx.ext.stomp.StompServerOptions
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
/**
 * Defines a STOMP server. STOMP servers delegates to a {@link io.vertx.groovy.ext.stomp.StompServerHandler} that let customize the behavior of
 * the server. By default, it uses a handler compliant with the STOMP specification, but let you change anything.
*/
@CompileStatic
public class StompServer {
  private final def io.vertx.ext.stomp.StompServer delegate;
  public StompServer(Object delegate) {
    this.delegate = (io.vertx.ext.stomp.StompServer) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Creates a {@link io.vertx.groovy.ext.stomp.StompServer} based on the default Stomp Server implementation.
   * @param vertx the vert.x instance to use
   * @param options the server options (see <a href="../../../../../../../cheatsheet/StompServerOptions.html">StompServerOptions</a>)
   * @return the created {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public static StompServer create(Vertx vertx, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(io.vertx.ext.stomp.StompServer.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, options != null ? new io.vertx.ext.stomp.StompServerOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), io.vertx.groovy.ext.stomp.StompServer.class);
    return ret;
  }
  /**
   * Creates a {@link io.vertx.groovy.ext.stomp.StompServer} based on the default Stomp Server implementation.
   * @param vertx the vert.x instance to use
   * @param netServer the Net server used by the STOMP server
   * @return the created {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public static StompServer create(Vertx vertx, NetServer netServer) {
    def ret = InternalHelper.safeCreate(io.vertx.ext.stomp.StompServer.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, netServer != null ? (io.vertx.core.net.NetServer)netServer.getDelegate() : null), io.vertx.groovy.ext.stomp.StompServer.class);
    return ret;
  }
  /**
   * Creates a {@link io.vertx.groovy.ext.stomp.StompServer} based on the default Stomp Server implementation.
   * @param vertx the vert.x instance to use
   * @param net the Net server used by the STOMP server
   * @param options the server options (see <a href="../../../../../../../cheatsheet/StompServerOptions.html">StompServerOptions</a>)
   * @return the created {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public static StompServer create(Vertx vertx, NetServer net, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(io.vertx.ext.stomp.StompServer.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, net != null ? (io.vertx.core.net.NetServer)net.getDelegate() : null, options != null ? new io.vertx.ext.stomp.StompServerOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), io.vertx.groovy.ext.stomp.StompServer.class);
    return ret;
  }
  /**
   * Creates a {@link io.vertx.groovy.ext.stomp.StompServer} based on the default Stomp Server implementation, and use the default options.
   * @param vertx the vert.x instance to use
   * @return the created {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public static StompServer create(Vertx vertx) {
    def ret = InternalHelper.safeCreate(io.vertx.ext.stomp.StompServer.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null), io.vertx.groovy.ext.stomp.StompServer.class);
    return ret;
  }
  /**
   * Configures the {@link io.vertx.groovy.ext.stomp.StompServerHandler}. You must calls this method before calling the {@link io.vertx.groovy.ext.stomp.StompServer#listen} method.
   * @param handler the handler
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer handler(StompServerHandler handler) {
    delegate.handler(handler != null ? (io.vertx.ext.stomp.StompServerHandler)handler.getDelegate() : null);
    return this;
  }
  /**
   * Connects the STOMP server to the given port.
   * @param port the port
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen(int port) {
    delegate.listen(port);
    return this;
  }
  /**
   * Connects the STOMP server to the given port / interface.
   * @param port the port
   * @param host the interface
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen(int port, String host) {
    delegate.listen(port, host);
    return this;
  }
  /**
   * Connects the STOMP server to the port / host configured in the server options.
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen() {
    delegate.listen();
    return this;
  }
  /**
   * Connects the STOMP server default port (61613) and network interface (<code>0.0.0.0</code>). Once the socket
   * it bounds calls the given handler with the result. The result may be a failure if the socket is already used.
   * @param handler the handler to call with the result
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen(Handler<AsyncResult<StompServer>> handler) {
    delegate.listen(handler != null ? new Handler<AsyncResult<io.vertx.ext.stomp.StompServer>>() {
      public void handle(AsyncResult<io.vertx.ext.stomp.StompServer> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), io.vertx.groovy.ext.stomp.StompServer.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  /**
   * Connects the STOMP server to the given port. This method use the default host (<code>0.0.0.0</code>). Once the socket
   * it bounds calls the given handler with the result. The result may be a failure if the socket is already used.
   * @param port the port
   * @param handler the handler to call with the result
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen(int port, Handler<AsyncResult<StompServer>> handler) {
    delegate.listen(port, handler != null ? new Handler<AsyncResult<io.vertx.ext.stomp.StompServer>>() {
      public void handle(AsyncResult<io.vertx.ext.stomp.StompServer> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), io.vertx.groovy.ext.stomp.StompServer.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  /**
   * Connects the STOMP server to the given port / interface. Once the socket it bounds calls the given handler with
   * the result. The result may be a failure if the socket is already used.
   * @param port the port
   * @param host the host / interface
   * @param handler the handler to call with the result
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer listen(int port, String host, Handler<AsyncResult<StompServer>> handler) {
    delegate.listen(port, host, handler != null ? new Handler<AsyncResult<io.vertx.ext.stomp.StompServer>>() {
      public void handle(AsyncResult<io.vertx.ext.stomp.StompServer> ar) {
        if (ar.succeeded()) {
          handler.handle(io.vertx.core.Future.succeededFuture(InternalHelper.safeCreate(ar.result(), io.vertx.groovy.ext.stomp.StompServer.class)));
        } else {
          handler.handle(io.vertx.core.Future.failedFuture(ar.cause()));
        }
      }
    } : null);
    return this;
  }
  /**
   * Closes the server.
   * @param completionHandler handler called once the server has been stopped
   */
  public void close(Handler<AsyncResult<Void>> completionHandler) {
    delegate.close(completionHandler);
  }
  /**
   * Closes the server.
   */
  public void close() {
    delegate.close();
  }
  /**
   * Checks whether or not the server is listening.
   * @return <code>true</code> if the server is listening, <code>false</code> otherwise
   */
  public boolean isListening() {
    def ret = delegate.isListening();
    return ret;
  }
  /**
   * Gets the port on which the server is listening.
   * <p/>
   * This is useful if you bound the server specifying 0 as port number signifying an ephemeral port.
   * @return the port
   */
  public int actualPort() {
    def ret = delegate.actualPort();
    return ret;
  }
  /**
   * @return the server options
   * @return  (see <a href="../../../../../../../cheatsheet/StompServerOptions.html">StompServerOptions</a>)
   */
  public Map<String, Object> options() {
    def ret = (Map<String, Object>)InternalHelper.wrapObject(delegate.options()?.toJson());
    return ret;
  }
  /**
   * @return the instance of vert.x used by the server.
   * @return 
   */
  public Vertx vertx() {
    def ret = InternalHelper.safeCreate(delegate.vertx(), io.vertx.groovy.core.Vertx.class);
    return ret;
  }
  /**
   * @return the {@link io.vertx.groovy.ext.stomp.StompServerHandler} used by this server.
   * @return 
   */
  public StompServerHandler stompHandler() {
    def ret = InternalHelper.safeCreate(delegate.stompHandler(), io.vertx.groovy.ext.stomp.StompServerHandler.class);
    return ret;
  }
  /**
   * Gets the  able to manage web socket connections. If the web socket bridge is disabled, it returns
   * <code>null</code>.
   * @return the handler that can be passed to {@link io.vertx.groovy.core.http.HttpServer#websocketHandler}.
   */
  public Handler<ServerWebSocket> webSocketHandler() {
    def ret = new Handler<ServerWebSocket>() {
      public void handle(ServerWebSocket event_) {
        delegate.webSocketHandler().handle((io.vertx.core.http.ServerWebSocket)event_.getDelegate());
      }
    };
    return ret;
  }
  /**
   * Configures the handler that is invoked every time a frame is going to be written to the "wire". It lets you log
   * the frames, but also adapt the frame if needed.
   * @param handler the handler, must not be <code>null</code>
   * @return the current {@link io.vertx.groovy.ext.stomp.StompServer}
   */
  public StompServer writingFrameHandler(Handler<ServerFrame> handler) {
    delegate.writingFrameHandler(handler != null ? new Handler<io.vertx.ext.stomp.ServerFrame>(){
      public void handle(io.vertx.ext.stomp.ServerFrame event) {
        handler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.ext.stomp.ServerFrame.class));
      }
    } : null);
    return this;
  }
}
