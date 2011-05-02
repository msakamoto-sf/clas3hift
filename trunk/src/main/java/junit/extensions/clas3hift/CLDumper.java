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
package junit.extensions.clas3hift;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Field;

public class CLDumper {
    public synchronized static void dump(JavaClass jc) {
        try {
            String s = jc.getClassName();
            System.out.println("==============================================");
            System.out.println("classname = " + s);
            s = jc.getSuperclassName();
            System.out.println("superclassname = " + s);
            System.out.println("----------------------------------------------");
            String[] interfaces = jc.getInterfaceNames();
            for (int i = 0; i < interfaces.length; i++) {
                System.out.println("interface[" + i + "] = " + interfaces[i].toString());
            }
            System.out.println("----------------------------------------------");
            Field[] fields = jc.getFields();
            for (int i = 0; i < fields.length; i++) {
                System.out.println("fields[" + i + "] = " + fields[i].toString());
                System.out.println("  name = " + fields[i].getName());
                System.out.println("  sign = " + fields[i].getSignature());
                System.out.println("  synthetic = " + fields[i].isSynthetic());
            }
            System.out.println("----------------------------------------------");
            Method[] methods = jc.getMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println("methods[" + i + "] = " + methods[i].toString());
                System.out.println("  name = " + methods[i].getName());
                System.out.println("  sign = " + methods[i].getSignature());
                System.out.println("  synthetic = " + methods[i].isSynthetic());
                System.out.println("  ret = " + methods[i].getReturnType());
                System.out.println("  code = " + methods[i].getCode());
            }
            System.out.println("==============================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void dump(Class clazz) {
        try {
            JavaClass jc = Repository.lookupClass(clazz);
            CLDumper.dump(jc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
