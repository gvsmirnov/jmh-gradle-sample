package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;

public class ParserBenchmark {

    @Benchmark
    public void measureBaseline(Json json, Blackhole bh) {
        json.text.chars().forEach(bh::consume);
    }

    @Benchmark
    public void measureSax_pre_check(Json json) throws IOException {
        json.parser.nextToken();
        JsonToken token;

        while ((token = json.parser.nextToken()) != null) {
            if (token != JsonToken.FIELD_NAME) {
                break;
            }
            json.parser.nextToken();
        }
    }

    @Benchmark
    public void measureSax_post_check(Json json) throws IOException {
        json.parser.nextToken();
        JsonToken token;

        while ((token = json.parser.nextToken()) != null) {
            json.parser.nextToken();
            if (token != JsonToken.FIELD_NAME) {
                break;
            }
        }
    }

    @State(Scope.Thread)
    public static class Json {

        @Param({"1", "256", "65536"})
        public long size;


        private String text;
        private JsonParser parser;

        @Setup
        public void createText() {
            StringBuilder builder = new StringBuilder("{");

            for (int i = 0; i < size; i++) {
                builder.append("\"key-" + i + "\": " + i + " ,");
            }

            builder.append("\"dummy\": -1}");

            text = builder.toString();
        }

        @Setup(Level.Invocation)
        public void init() throws IOException {
            parser = new JsonFactory().createParser(text);
        }
    }

/*

$ java -jar build/distributions/jmh-gradle-sample-0.0.1-benchmarks.jar ".*Parser.*" -bm avgt -tu us

Benchmark                                 (size)   Mode   Samples         Mean   Mean error    Units
s.ParserBenchmark.measureBaseline              1   avgt       200        0.218        0.001    us/op
s.ParserBenchmark.measureSax_post_check        1   avgt       200        0.319        0.001    us/op
s.ParserBenchmark.measureSax_pre_check         1   avgt       200        0.397        0.001    us/op
s.ParserBenchmark.measureBaseline            256   avgt       200        8.237        0.015    us/op
s.ParserBenchmark.measureSax_post_check      256   avgt       200       71.461        0.102    us/op
s.ParserBenchmark.measureSax_pre_check       256   avgt       200       71.884        0.316    us/op
s.ParserBenchmark.measureBaseline          65536   avgt       200     2670.910        2.389    us/op
s.ParserBenchmark.measureSax_post_check    65536   avgt       200    27876.007      191.216    us/op
s.ParserBenchmark.measureSax_pre_check     65536   avgt       200    28228.497      219.765    us/op

 */
}
