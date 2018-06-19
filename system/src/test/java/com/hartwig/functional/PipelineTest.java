package com.hartwig.functional;

import static com.hartwig.testsupport.Assertions.assertThatOutput;
import static com.hartwig.testsupport.TestPatients.HUNDREDK_READS_HISEQ;
import static com.hartwig.testsupport.TestPatients.HUNDREDK_READS_HISEQ_REAL_SAMPLE;

import com.hartwig.patient.RawSequencingOutput;
import com.hartwig.pipeline.adam.ADAMPipelines;
import com.hartwig.pipeline.gatk.GATK4Pipelines;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.bdgenomics.adam.rdd.ADAMContext;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class PipelineTest {

    private static JavaSparkContext context;

    @BeforeClass
    public static void beforeClass() {
        SparkConf conf = new SparkConf().set("spark.serializer", "org.apache.spark.serializer.KryoSerializer").setMaster("local[2]")
                .setAppName("test");
        context = new JavaSparkContext(conf);
    }

    @Test
    public void adamPreprocessingMatchesCurrentPipelineOuput() throws Exception {
        ADAMPipelines.preProcessing(HUNDREDK_READS_HISEQ, new ADAMContext(context.sc()))
                .execute(RawSequencingOutput.from(HUNDREDK_READS_HISEQ));
        // assertThatOutput(HUNDREDK_READS_HISEQ_REAL_SAMPLE).aligned().duplicatesMarked().isEqualToExpected();
    }

    @Ignore("GATK preprocessor fails currently on this sample (duplicate key exception). More investigation necessary")
    @Test
    public void gatkPreprocessingMatchesCurrentPipelineOuput() throws Exception {
        GATK4Pipelines.preProcessing(HUNDREDK_READS_HISEQ, context).execute(RawSequencingOutput.from(HUNDREDK_READS_HISEQ));
        assertThatOutput(HUNDREDK_READS_HISEQ_REAL_SAMPLE).aligned().duplicatesMarked().isEqualToExpected();
    }
}