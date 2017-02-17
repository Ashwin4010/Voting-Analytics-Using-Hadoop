
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author rajesh
 */
public class ElectionReducerA extends MapReduceBase implements Reducer<Text, IntWritable, Text, LongWritable>{

    @Override
    public void reduce(Text key, Iterator<IntWritable> val, OutputCollector<Text, LongWritable> outputCollector, Reporter reporter) throws IOException {
        long total=0;
        while(val.hasNext()) {
            total+=val.next().get();
        }
        outputCollector.collect(key,new LongWritable(total));
    }
    
}
