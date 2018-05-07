package ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by debasish paul on 07-05-2018.
 */
public class IgniteLoadtest {
    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteLoadtest.class);

    public static void main(String[] args) {
        org.apache.ignite.configuration.IgniteConfiguration igniteConfiguration = new org.apache.ignite.configuration.IgniteConfiguration();

//        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
//        DataRegionConfiguration dataRegionConfiguration = new DataRegionConfiguration();
//        dataRegionConfiguration.setName("Default_Region");
//        dataRegionConfiguration.setMaxSize(4l * 1024 * 1024);
//        storageCfg.setDataRegionConfigurations(dataRegionConfiguration);
//        igniteConfiguration.setDataStorageConfiguration(storageCfg);


        IgniteLogger log = new Slf4jLogger();
        igniteConfiguration.setGridLogger(log);

        igniteConfiguration.setWorkDirectory("D:/test_project/ignite-workDir");


        Ignite ignite = Ignition.start(igniteConfiguration);
        LOGGER.info(">>>>>>>>>>>>>>>>Ignite Cache Started successfully");
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 300000; i++) {
            IgniteCache<String, Object> cache = ignite.getOrCreateCache("my-cache");
            cache.put(String.valueOf(i), i);
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("Time taken: " + Duration.between(start, end));
    }
}
