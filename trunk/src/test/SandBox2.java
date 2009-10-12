/*
 * Copyright 2009 sakamoto.gsyc.3s@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package test;

import static org.junit.Assert.*;
import junit.extensions.clas3hift.SandBox;
import java.util.Map;
import test.stub.Foo0;

public class SandBox2 implements SandBox {
    public void sandbox(Map<String, Object> result) {
        assertEquals("I am Foo0", Foo0.name);
        assertEquals("My name is Foo0", Foo0.getName());
    }
}
