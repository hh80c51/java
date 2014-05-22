package javase8sample.chapter11.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by benhail on 2014/5/21.
 */
public class Nashorn1 {
    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval("print('Hello World!');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
