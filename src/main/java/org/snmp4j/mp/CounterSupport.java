/*_############################################################################
  _## 
  _##  SNMP4J 2 - CounterSupport.java  
  _## 
  _##  Copyright (C) 2003-2016  Frank Fock and Jochen Katz (SNMP4J.org)
  _##  
  _##  Licensed under the Apache License, Version 2.0 (the "License");
  _##  you may not use this file except in compliance with the License.
  _##  You may obtain a copy of the License at
  _##  
  _##      http://www.apache.org/licenses/LICENSE-2.0
  _##  
  _##  Unless required by applicable law or agreed to in writing, software
  _##  distributed under the License is distributed on an "AS IS" BASIS,
  _##  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  _##  See the License for the specific language governing permissions and
  _##  limitations under the License.
  _##  
  _##########################################################################*/


package org.snmp4j.mp;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import org.snmp4j.event.*;

/**
 * The <code>CounterSupport</code> class provides support to fire
 * {@link CounterEvent} to registered listeners.
 * @author Frank Fock
 * @version 1.0
 */
public class CounterSupport {

  protected static CounterSupport instance = null;
  private transient List<CounterListener> counterListeners = new CopyOnWriteArrayList<>();

  protected CounterSupport() {
  }

  /**
   * Gets the counter support singleton.
   * @return
   *    the <code>CounterSupport</code> instance.
   */
  public static CounterSupport getInstance() {
    if (instance == null) {
      instance = new CounterSupport();
    }
    return instance;
  }

  /**
   * Adds a <code>CounterListener</code>.
   * @param listener
   *    a <code>CounterListener</code> instance that needs to be informed when
   *    a counter needs to be incremented.
   */
  public void addCounterListener(CounterListener listener) {
    if (!counterListeners.contains(listener)) {
      counterListeners.add(listener);
    }
  }

  /**
   * Removes a previously added <code>CounterListener</code>.
   * @param listener
   *    a <code>CounterListener</code> instance.
   */
  public void removeCounterListener(CounterListener listener) {
    counterListeners.remove(listener);
  }

  /**
   * Inform all registered listeners that the supplied counter needs to be
   * incremented.
   * @param event
   *    a <code>CounterEvent</code> containing information about the counter to
   *    be incremented.
   */
  public void fireIncrementCounter(CounterEvent event) {
    for (CounterListener l: counterListeners) {
      l.incrementCounter(event);
    }
  }
}
