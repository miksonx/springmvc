
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.util.EntityPropertiesBuilders;
import com.miksonx.bamboo.PlanSpec;

import org.junit.Test;

public class PlanSpecTest {
    @Test
    public void checkYourPlanOffline() throws PropertiesValidationException {
        Plan plan = new PlanSpec().plan();

        EntityPropertiesBuilders.build(plan);
    }
}
