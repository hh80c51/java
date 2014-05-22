package javase8sample.chapter12.nashorn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by benhail on 2014/5/21.
 */
public class Nashorn1 {
    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    }
}
