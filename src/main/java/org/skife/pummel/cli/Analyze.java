package org.skife.pummel.cli;


import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.skife.cli.Arguments;
import org.skife.cli.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

@Command(name="analyze")
public class Analyze  implements Callable<Void>
{

    @Arguments
    public File urlFile;

    public Void call() throws Exception
    {


        final BufferedReader in ;
        if (urlFile != null) {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(urlFile)));
        }
        else {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            long val = Long.parseLong(line);
            stats.addValue(val);
        }

        System.out.printf("n\t%d\n", stats.getN());
        System.out.printf("max\t%f\n", stats.getMax());
        System.out.printf("mean\t%f\n", stats.getMean());
        System.out.printf("99.9%%\t%f\n", stats.getPercentile(99.9));
        System.out.printf("99%%\t%f\n", stats.getPercentile(99));
        System.out.printf("90%%\t%f\n", stats.getPercentile(90));
        System.out.printf("80%%\t%f\n", stats.getPercentile(80));
        System.out.printf("50%%\t%f\n", stats.getPercentile(50));
        return null;
    }
}
