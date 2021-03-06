/*
 * File:                BayesianRegression.java
 * Authors:             Kevin R. Dixon
 * Company:             Sandia National Laboratories
 * Project:             Cognitive Foundry
 * 
 * Copyright Apr 1, 2010, Sandia Corporation.
 * Under the terms of Contract DE-AC04-94AL85000, there is a non-exclusive
 * license for use of this work by or on behalf of the U.S. Government.
 * Export of this program may require a license from the United States
 * Government. See CopyrightHistory.txt for complete details.
 * 
 */

package gov.sandia.cognition.statistics.bayesian;

import gov.sandia.cognition.annotation.PublicationReference;
import gov.sandia.cognition.annotation.PublicationReferences;
import gov.sandia.cognition.annotation.PublicationType;
import gov.sandia.cognition.evaluator.Evaluator;
import gov.sandia.cognition.learning.data.InputOutputPair;
import gov.sandia.cognition.math.matrix.Vector;
import gov.sandia.cognition.math.matrix.Vectorizable;
import gov.sandia.cognition.statistics.ClosedFormDistribution;
import gov.sandia.cognition.statistics.Distribution;

/**
 * A type of regression algorithm maps a Vector space, and the
 * weights of this Vector space are represented as a posterior distribution
 * given the observed InputOutputPairs.  The system can also estimate
 * the predictive distribution of future data given the weight posterior
 * for a desired input value.
 * @param <OutputType>
 * Type of outputs to consider, typically a Double
 * @param <PosteriorType>
 * Posterior distribution of the weights given the observed InputOutputPairs
 * @author Kevin R. Dixon
 * @since 3.0
 */
@PublicationReferences(
    references={
        @PublicationReference(
            author="Christopher M. Bishop",
            title="Pattern Recognition and Machine Learning",
            type=PublicationType.Book,
            year=2006,
            pages={152,159}
        )
        ,
        @PublicationReference(
            author="Hanna M. Wallach",
            title="Introduction to Gaussian Process Regression",
            type=PublicationType.Misc,
            year=2005,
            url="http://www.cs.umass.edu/~wallach/talks/gp_intro.pdf"
        )
        ,
        @PublicationReference(
            author="Wikipedia",
            title="Bayesian linear regression",
            type=PublicationType.WebPage,
            year=2010,
            url="http://en.wikipedia.org/wiki/Bayesian_linear_regression"
        )
    }
)
public interface BayesianRegression<OutputType,PosteriorType extends Distribution<? extends Vector>>
    extends BayesianEstimator<InputOutputPair<? extends Vectorizable, OutputType>, Vector, PosteriorType>
{

    /**
     * Creates the distribution from which the outputs are generated, given
     * the weights and the input to consider.
     * @param input
     * Input to condition on
     * @param weights
     * Weights that determine the mean
     * @return
     * Conditional distribution from which outputs are generated.
     */
    public Distribution<OutputType> createConditionalDistribution(
        final Vectorizable input,
        final Vector weights );

    /**
     * Creates the predictive distribution of outputs given the weight posterior
     * @param posterior
     * Posterior distribution of weights.
     * @return
     * Predictive distribution of outputs given the posterior.
     */
    public Evaluator<? super Vectorizable,? extends ClosedFormDistribution<OutputType>> createPredictiveDistribution(
        final PosteriorType posterior );
    
}
