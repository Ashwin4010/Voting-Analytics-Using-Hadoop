
import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author rajesh
 */
public class ElectionCombiner extends MapReduceBase implements Reducer<Text, DoubleWritable, Text, DoubleWritable>{

    @Override
    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, DoubleWritable> outCollector, Reporter reporter) throws IOException {
        double total=0.0;
        while(values.hasNext()) {
            total+=values.next().get();
        }
        outCollector.collect(new Text("mean number of voters"),new DoubleWritable(total));
    }
}
