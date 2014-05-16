/*
 * Copyright 2014 benhaile.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.benhaile.javase8.sample.nashorn;

/**
 *
 * @author benhail
 */
import javax.script.*;

public class NashornEngine {

    public static void main(String[] args) {
        ScriptEngineManager m = new ScriptEngineManager();
        ScriptEngine e = m.getEngineByName("nashorn");
        try {
            e.put("x", 10);
            e.put("y", 20);
            e.eval("var z = x+ y;");
            System.out.println(((Number) e.get("z")).intValue());
        } catch (final ScriptException ex) {
            System.err.println(ex);
        }
    }

}
