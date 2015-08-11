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
import java.util.List
import io.vertx.ext.stomp.Frame
/**
 * Handler called when a <code>ACK</code> or <code>NACK</code> frame is received. The handler receives the {@link io.vertx.groovy.ext.stomp.Subscription}
 * and the impacted messages. This list of messages depends on the type of acknowledgment used by the subscription.
 * Subscriptions using <code>auto</code> do not call this handler (because there are no <code>ACK/NACK</code> in @{code auto}).
 * Subscriptions using the <code>client</code> mode receives all messages that were waiting for acknowledgment that were
 * sent before the acknowledged messages. The list also contains the acknowledged message. This is a cumulative
 * acknowledgement. Subscriptions using the <code>client-individual</code> mode receives a singleton list containing only
 * the acknowledged message.
*/
@CompileStatic
public interface AcknowledgmentHandler {
  public Object getDelegate();
  void handle(Subscription subscription, List<Map<String, Object>> frames);
}

@CompileStatic
class AcknowledgmentHandlerImpl implements AcknowledgmentHandler {
  private final def io.vertx.ext.stomp.AcknowledgmentHandler delegate;
  public AcknowledgmentHandlerImpl(Object delegate) {
    this.delegate = (io.vertx.ext.stomp.AcknowledgmentHandler) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Called when a <code>ACK / NACK</code> frame is received.
   * @param subscription the subscription
   * @param frames the impacted frames. If the subscription uses the <code>client</code> mode, it contains all impacted messages (cumulative acknowledgment). In <code>client-individual</code> mode, the list contains only the acknowledged frame.
   */
  public void handle(Subscription subscription, List<Map<String, Object>> frames) {
    ((io.vertx.ext.stomp.AcknowledgmentHandler) this.delegate).handle((io.vertx.ext.stomp.Subscription)subscription.getDelegate(), frames.collect({underpants -> new Frame(new JsonObject(underpants))}));
  }
}