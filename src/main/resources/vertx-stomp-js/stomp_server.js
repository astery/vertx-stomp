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

/** @module vertx-stomp-js/stomp_server */
var utils = require('vertx-js/util/utils');
var StompServerHandler = require('vertx-stomp-js/stomp_server_handler');
var ServerWebSocket = require('vertx-js/server_web_socket');
var NetServer = require('vertx-js/net_server');
var Vertx = require('vertx-js/vertx');
var ServerFrame = require('vertx-stomp-js/server_frame');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JStompServer = io.vertx.ext.stomp.StompServer;
var StompServerOptions = io.vertx.ext.stomp.StompServerOptions;

/**

 @class
*/
var StompServer = function(j_val) {

  var j_stompServer = j_val;
  var that = this;

  /**
   Configures the {@link StompServerHandler}. You must calls this method before calling the {@link StompServer#listen} method.

   @public
   @param handler {StompServerHandler} the handler 
   @return {StompServer} the current {@link StompServer}
   */
  this.handler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'object' && __args[0]._jdel) {
      j_stompServer["handler(io.vertx.ext.stomp.StompServerHandler)"](handler._jdel);
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Connects the STOMP server to the given port / interface. Once the socket it bounds calls the given handler with
   the result. The result may be a failure if the socket is already used.

   @public
   @param port {number} the port 
   @param host {string} the host / interface 
   @param handler {function} the handler to call with the result 
   @return {StompServer} the current {@link StompServer}
   */
  this.listen = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_stompServer["listen()"]();
      return that;
    }  else if (__args.length === 1 && typeof __args[0] ==='number') {
      j_stompServer["listen(int)"](__args[0]);
      return that;
    }  else if (__args.length === 1 && typeof __args[0] === 'function') {
      j_stompServer["listen(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](utils.convReturnVertxGen(ar.result(), StompServer), null);
      } else {
        __args[0](null, ar.cause());
      }
    });
      return that;
    }  else if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'string') {
      j_stompServer["listen(int,java.lang.String)"](__args[0], __args[1]);
      return that;
    }  else if (__args.length === 2 && typeof __args[0] ==='number' && typeof __args[1] === 'function') {
      j_stompServer["listen(int,io.vertx.core.Handler)"](__args[0], function(ar) {
      if (ar.succeeded()) {
        __args[1](utils.convReturnVertxGen(ar.result(), StompServer), null);
      } else {
        __args[1](null, ar.cause());
      }
    });
      return that;
    }  else if (__args.length === 3 && typeof __args[0] ==='number' && typeof __args[1] === 'string' && typeof __args[2] === 'function') {
      j_stompServer["listen(int,java.lang.String,io.vertx.core.Handler)"](__args[0], __args[1], function(ar) {
      if (ar.succeeded()) {
        __args[2](utils.convReturnVertxGen(ar.result(), StompServer), null);
      } else {
        __args[2](null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Closes the server.

   @public
   @param completionHandler {function} handler called once the server has been stopped 
   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_stompServer["close()"]();
    }  else if (__args.length === 1 && typeof __args[0] === 'function') {
      j_stompServer["close(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](null, null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Checks whether or not the server is listening.

   @public

   @return {boolean} <code>true</code> if the server is listening, <code>false</code> otherwise
   */
  this.isListening = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_stompServer["isListening()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Gets the port on which the server is listening.
   <p/>
   This is useful if you bound the server specifying 0 as port number signifying an ephemeral port.

   @public

   @return {number} the port
   */
  this.actualPort = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_stompServer["actualPort()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   @return the server options

   @public

   @return {Object}
   */
  this.options = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnDataObject(j_stompServer["options()"]());
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   @return the instance of vert.x used by the server.

   @public

   @return {Vertx}
   */
  this.vertx = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnVertxGen(j_stompServer["vertx()"](), Vertx);
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   @return the {@link StompServerHandler} used by this server.

   @public

   @return {StompServerHandler}
   */
  this.stompHandler = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnVertxGen(j_stompServer["stompHandler()"](), StompServerHandler);
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Gets the  able to manage web socket connections. If the web socket bridge is disabled, it returns
   <code>null</code>.

   @public

   @return {function} the handler that can be passed to {@link HttpServer#websocketHandler}.
   */
  this.webSocketHandler = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnHandler(j_stompServer["webSocketHandler()"](), function(result) { return result._jdel; });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Configures the handler that is invoked every time a frame is going to be written to the "wire". It lets you log
   the frames, but also adapt the frame if needed.

   @public
   @param handler {function} the handler, must not be <code>null</code> 
   @return {StompServer} the current {@link StompServer}
   */
  this.writingFrameHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_stompServer["writingFrameHandler(io.vertx.core.Handler)"](function(jVal) {
      handler(utils.convReturnVertxGen(jVal, ServerFrame));
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_stompServer;
};

/**
 Creates a {@link StompServer} based on the default Stomp Server implementation.

 @memberof module:vertx-stomp-js/stomp_server
 @param vertx {Vertx} the vert.x instance to use 
 @param net {NetServer} the Net server used by the STOMP server 
 @param options {Object} the server options 
 @return {StompServer} the created {@link StompServer}
 */
StompServer.create = function() {
  var __args = arguments;
  if (__args.length === 1 && typeof __args[0] === 'object' && __args[0]._jdel) {
    return utils.convReturnVertxGen(JStompServer["create(io.vertx.core.Vertx)"](__args[0]._jdel), StompServer);
  }else if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null)) {
    return utils.convReturnVertxGen(JStompServer["create(io.vertx.core.Vertx,io.vertx.ext.stomp.StompServerOptions)"](__args[0]._jdel, __args[1] != null ? new StompServerOptions(new JsonObject(JSON.stringify(__args[1]))) : null), StompServer);
  }else if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object' && __args[1]._jdel) {
    return utils.convReturnVertxGen(JStompServer["create(io.vertx.core.Vertx,io.vertx.core.net.NetServer)"](__args[0]._jdel, __args[1]._jdel), StompServer);
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object' && __args[1]._jdel && (typeof __args[2] === 'object' && __args[2] != null)) {
    return utils.convReturnVertxGen(JStompServer["create(io.vertx.core.Vertx,io.vertx.core.net.NetServer,io.vertx.ext.stomp.StompServerOptions)"](__args[0]._jdel, __args[1]._jdel, __args[2] != null ? new StompServerOptions(new JsonObject(JSON.stringify(__args[2]))) : null), StompServer);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = StompServer;