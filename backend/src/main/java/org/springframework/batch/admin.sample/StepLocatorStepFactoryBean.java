package org.springframework.batch.admin.sample;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.StepLocator;
import org.springframework.beans.factory.FactoryBean;

/**
 * Convenience factory for {@link org.springframework.batch.core.Step} instances given a {@link org.springframework.batch.core.step.StepLocator}.
 * Most implementations of {@link org.springframework.batch.core.Job} implement StepLocator, so that can be a
 * good starting point.
 *
 * @author Dave Syer
 *
 */
public class StepLocatorStepFactoryBean implements FactoryBean<Step> {

	public StepLocator stepLocator;

	public String stepName;

	/**
	 * @param stepLocator
	 */
	public void setStepLocator(StepLocator stepLocator) {
		this.stepLocator = stepLocator;
	}

	/**
	 * @param stepName
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	/**
	 *
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	public Step getObject() throws Exception {
		return stepLocator.getStep(stepName);
	}

	/**
	 * Tell clients that we are a factory for {@link org.springframework.batch.core.Step} instances.
	 *
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	public Class<? extends Step> getObjectType() {
		return Step.class;
	}

	/**
	 * Always return true as optimization for bean factory.
	 *
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	public boolean isSingleton() {
		return true;
	}

}
