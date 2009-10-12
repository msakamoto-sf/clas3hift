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

import java.util.Map;
import java.util.HashMap;

public class Clas3hift {

    protected junit.extensions.clas3hift.CL _cl;
    protected Map<String, Object> _results;

    public Clas3hift()
    {
        _cl = new junit.extensions.clas3hift.CL(
                Clas3hift.class.getClassLoader());
        _results = new HashMap<String, Object>();
    }

    public void register(String base_name, String alias) 
        throws ClassNotFoundException 
    {
        this._cl.register(base_name, alias);
    }

    public void play(SandBox sb) {
        SandBox sb_ = this._cl.surround(sb);
        sb_.sandbox(this._results);
    }

    public Object result(String key) {
        return this._results.get(key);
    }
}
