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

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import junit.extensions.clas3hift.SandBox;
import junit.extensions.clas3hift.Clas3hift;

import java.util.Map;
import test.stub.Foo0;

public class TestClas3hift {
    @Test
        public void Clas3hift_0() {
            Clas3hift cs = new Clas3hift();
            SandBox0 sb = new SandBox0();
            cs.play(sb);
            assertEquals("I am Foo0", (String)cs.result("test1"));
            assertEquals("My name is Foo0", (String)cs.result("test2"));
        }

    @Test
        public void Clas3hift_1() throws ClassNotFoundException {
            Clas3hift cs = new Clas3hift();
            cs.register("test.stub.Foo0", "test.stub.Foo1");
            SandBox1 sb = new SandBox1();
            cs.play(sb);
        }

    @Test
        public void Clas3hift_2() throws ClassNotFoundException {
            Clas3hift cs = new Clas3hift();
            cs.register("test.stub.Foo0", "test.stub.Foo2");
            SandBox2 sb = new SandBox2();
            cs.play(sb);
        }

    @Ignore
        // unsupported test code examples...
        public void Clas3hift_DONTTEST() throws ClassNotFoundException {
            Clas3hift cs = new Clas3hift();
            cs.register("test.stub.Foo0", "test.stub.FooDONTTEST");

            // DON'T SUPPORT INNER/NESTED SANDBOX CLASS!!!
            cs.play(new SandBox() {

                public void sandbox(Map<String, Object> result) {

                    // DON'T USE new !! cause CPU hang-up!!
                    Foo0 f = new Foo0();

                    // static fields aren't supported!!!
                    assertEquals("DONT TEST", Foo0.name);
                }

            });
        }
}
