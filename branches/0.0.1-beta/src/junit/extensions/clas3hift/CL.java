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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import junit.extensions.PA;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;

public class CL extends ClassLoader {

    protected Map<String, Class> _replaces = new HashMap<String, Class>();

    public CL(ClassLoader appCL)
    {
        super(appCL);
    }

    public void register(String base_name, String alias) 
        throws ClassNotFoundException 
    {
        JavaClass jc = Repository.lookupClass(base_name);

        long now = System.currentTimeMillis();
        Random r = new Random(now);
        long rnd = r.nextLong();
        StringBuilder sb = new StringBuilder(base_name);
        sb.append("$");
        sb.append(String.valueOf(now));
        sb.append("$");
        sb.append(String.valueOf(rnd));
        String hidden_name = sb.toString();

        ClassGen c = new ClassGen(jc);
        c.setClassName(hidden_name);
        jc = c.getJavaClass();

        byte[] baseBytes = jc.getBytes();
        Class hiddenBaseClazz = defineClass(
                hidden_name, 
                baseBytes, 
                0, 
                baseBytes.length
                );
        resolveClass(hiddenBaseClazz);

        jc = Repository.lookupClass(alias);
        c = new ClassGen(jc);
        c.setClassName(base_name);
        c.setSuperclassName(hidden_name);
        jc = c.getJavaClass();
        baseBytes = jc.getBytes();

        Class aliasClazz = defineClass(
                base_name, 
                baseBytes, 
                0, 
                baseBytes.length
                );
        resolveClass(aliasClazz);

        _replaces.put(base_name, aliasClazz);
    }

    public Class cloneClass(Class src) 
        throws Throwable
    {
        String class_name = src.getName();
        String resource_name = class_name.replace('.', '/') + ".class";

        Class clonedClass = null;
        DataInputStream dis = null;
        try {
            InputStream is = getClass().getClassLoader()
                .getResourceAsStream(resource_name);
            if (null == is) {
                return null;
            }
            dis = new DataInputStream(is);
            byte[] bytes = new byte[is.available()];
            dis.readFully(bytes);

            clonedClass = defineClass(class_name, bytes, 0, bytes.length);
            resolveClass(clonedClass);

        } catch (Throwable e) {
            throw e;
        } finally {
            if (null != dis) {
                try { dis.close(); } catch (Exception e) {}
            }
        }
        return clonedClass;
    }

    public SandBox surround(SandBox sb) 
    {
        Class srcClass = sb.getClass();
        SandBox newSb = null;
        try {
            Class newClass = this.cloneClass(srcClass);
            /* if sandbox is defined as innerclass, its constructor 
             * is private. so, powered by PA, call private constuctor.
             */
            newSb = (SandBox)PA.instantiate(newClass, new Object[] {});
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return newSb;
    }

    protected final Class loadClass(String name, boolean resolve)
        throws ClassNotFoundException 
    {
        Class clazz = (Class)_replaces.get(name);
        if (null != clazz) {
            return clazz;
        } else {
            ClassLoader parent = getParent();
            Class r = super.loadClass(name, resolve);
            return r;
        }
    }
}
