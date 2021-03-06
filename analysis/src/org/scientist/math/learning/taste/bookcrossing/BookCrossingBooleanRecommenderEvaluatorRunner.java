package org.scientist.math.learning.taste.bookcrossing;
import org.apache.commons.cli2.OptionException;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.scientist.math.learning.taste.*;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public final class BookCrossingBooleanRecommenderEvaluatorRunner {

  private static final Logger log = LoggerFactory.getLogger(BookCrossingBooleanRecommenderEvaluatorRunner.class);

  private BookCrossingBooleanRecommenderEvaluatorRunner() {
    // do nothing
  }

  public static void main(String... args) throws IOException, TasteException, OptionException {
    RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
    File ratingsFile = TasteOptionParser.getRatings(args);
    DataModel model =
        ratingsFile == null ? new BookCrossingDataModel(true) : new BookCrossingDataModel(ratingsFile, true);

    IRStatistics evaluation = evaluator.evaluate(
        new BookCrossingBooleanRecommenderBuilder(),
        new BookCrossingDataModelBuilder(),
        model,
        null,
        3,
        Double.NEGATIVE_INFINITY,
        1.0);

    log.info(String.valueOf(evaluation));
  }

}