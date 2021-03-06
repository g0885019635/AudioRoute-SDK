/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 * Copyright 2019 n-Track S.r.l.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.ntrack.audioroute;

/**
 * Checked exception for Java-style handling of error codes from Audioroute. The {@link Audioroute}
 * class itself doesn't throw exceptions but returns C-style error codes because exceptions can't be
 * thrown via AIDL. If, however, you prefer to use exceptions in Java, you can convert error codes
 * by wrapping calls to Audioroute in the throwOnError method below.
 */
public class AudiorouteException extends Exception {

  // WARNING: Do not change these constants without updating references in audioroute.c.
  public static final int SUCCESS = 0;
  public static final int FAILURE = -1;
  public static final int INVALID_PARAMETERS = -2;
  public static final int NO_SUCH_MODULE = -3;
  public static final int MODULE_NAME_TAKEN = -4;
  public static final int TOO_MANY_MODULES = -5;
  public static final int PORT_OUT_OF_RANGE = -6;
  public static final int TOO_MANY_CONNECTIONS = -7;
  public static final int CYCLIC_DEPENDENCY = -8;
  public static final int OUT_OF_BUFFER_SPACE = -9;
  public static final int PROTOCOL_VERSION_MISMATCH = -10;

  private static final long serialVersionUID = 1L;
  private final int code;

  public AudiorouteException(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static int successOrFailure(int n) {
    return (n >= 0) ? SUCCESS : FAILURE;
  }

  public static int throwOnError(int code) throws AudiorouteException {
    if (code < 0) {
      throw new AudiorouteException(code);
    }
    return code;
  }
}
