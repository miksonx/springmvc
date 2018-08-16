package com.miksonx.bamboo;
import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.AtlassianModule;
import com.atlassian.bamboo.specs.api.builders.BambooKey;
import com.atlassian.bamboo.specs.api.builders.BambooOid;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact;
import com.atlassian.bamboo.specs.api.builders.plan.branches.BranchCleanup;
import com.atlassian.bamboo.specs.api.builders.plan.branches.PlanBranchManagement;
import com.atlassian.bamboo.specs.api.builders.plan.configuration.ConcurrentBuilds;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.api.builders.repository.VcsChangeDetection;
import com.atlassian.bamboo.specs.api.builders.task.AnyTask;
import com.atlassian.bamboo.specs.builders.repository.git.UserPasswordAuthentication;
import com.atlassian.bamboo.specs.builders.repository.github.GitHubRepository;
import com.atlassian.bamboo.specs.builders.repository.viewer.GitHubRepositoryViewer;
import com.atlassian.bamboo.specs.builders.task.CheckoutItem;
import com.atlassian.bamboo.specs.builders.task.MavenTask;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.util.MapBuilder;

@BambooSpec
public class PlanSpec {
    
    public Plan plan() {
        final Plan plan = new Plan(new Project()
                .oid(new BambooOid("g5pkihronyte"))
                .key(new BambooKey("SPR"))
                .name("SpringMvc")
                .description("SpringMVC"),
            "SpringMVC",
            new BambooKey("SM"))
            .oid(new BambooOid("g5fvawegu6f6"))
            .pluginConfigurations(new ConcurrentBuilds()
                    .useSystemWideDefault(false))
            .stages(new Stage("Default Stage")
                    .jobs(new Job("Default Job",
                            new BambooKey("JOB1"))
                            .artifacts(new Artifact()
                                    .name("webapp-0.0.1-todos.war")
                                    .copyPattern("**/*.war")
                                    .location("target")
                                    .shared(true))
                            .tasks(new VcsCheckoutTask()
                                    .description("Code collect")
                                    .checkoutItems(new CheckoutItem().defaultRepository()),
                                new MavenTask()
                                    .description("Build")
                                    .goal("clean verify install")
                                    .jdk("JDK 1.8")
                                    .executableLabel("Maven 3"),
                                new AnyTask(new AtlassianModule("ch.mibex.bamboo.sonar4bamboo:sonar4bamboo.maven3task"))
                                    .description("SpringSQ")
                                    .configuration(new MapBuilder()
                                            .put("incrementalFileForInclusionList", "")
                                            .put("chosenSonarConfigId", "1")
                                            .put("useGradleWrapper", "")
                                            .put("useNewGradleSonarQubePlugin", "")
                                            .put("sonarJavaSource", "")
                                            .put("sonarProjectName", "")
                                            .put("buildJdk", "JDK")
                                            .put("gradleWrapperLocation", "")
                                            .put("sonarLanguage", "")
                                            .put("sonarSources", "")
                                            .put("useGlobalSonarServerConfig", "true")
                                            .put("incrementalMode", "")
                                            .put("failBuildForBrokenQualityGates", "")
                                            .put("sonarTests", "")
                                            .put("incrementalNoPullRequest", "incrementalModeFailBuildField")
                                            .put("failBuildForSonarErrors", "")
                                            .put("sonarProjectVersion", "")
                                            .put("sonarBranch", "")
                                            .put("executable", "Maven 3")
                                            .put("illegalBranchCharsReplacement", "_")
                                            .put("failBuildForTaskErrors", "true")
                                            .put("incrementalModeNotPossible", "incrementalModeRunFullAnalysis")
                                            .put("sonarJavaTarget", "")
                                            .put("environmentVariables", "")
                                            .put("incrementalModeGitBranchPattern", "")
                                            .put("legacyBranching", "")
                                            .put("replaceSpecialBranchChars", "")
                                            .put("additionalProperties", "")
                                            .put("autoBranch", "true")
                                            .put("sonarProjectKey", "")
                                            .put("incrementalModeBambooUser", "")
                                            .put("overrideSonarBuildConfig", "")
                                            .put("workingSubDirectory", "")
                                            .build()),
                                new AnyTask(new AtlassianModule("org.jfrog.bamboo.bamboo-artifactory-plugin:artifactoryGenericTask"))
                                    .description("SpringAF")
                                    .configuration(new MapBuilder()
                                            .put("artifactory.generic.publishBuildInfo", "")
                                            .put("bintrayConfiguration", "")
                                            .put("bintray.licenses", "")
                                            .put("bintray.repository", "")
                                            .put("artifactory.generic.username", "admin")
                                            .put("artifactory.generic.specSourceChoice", "jobConfiguration")
                                            .put("artifactory.generic.resolveRepo", "")
                                            .put("artifactory.generic.deployPattern", "**/*.war")
                                            .put("artifactory.generic.envVarsExcludePatterns", "*password*,*secret*,*security*,*key*")
                                            .put("bintray.signMethod", "false")
                                            .put("builder.artifactoryGenericBuilder.artifactoryServerId", "0")
                                            .put("bintray.subject", "")
                                            .put("artifactory.generic.file", "")
                                            .put("artifactory.generic.useSpecsChoice", "legacyPatterns")
                                            .put("bintray.packageName", "")
                                            .put("artifactory.generic.includeEnvVars", "")
                                            .put("artifactory.generic.artifactSpecs", "")
                                            .put("artifactory.generic.password", "/* SENSITIVE INFORMATION */")
                                            .put("bintray.mavenSync", "")
                                            .put("artifactory.generic.jobConfiguration", "")
                                            .put("baseUrl", "http://127.0.1.1:8085")
                                            .put("artifactory.generic.envVarsIncludePatterns", "")
                                            .put("artifactory.generic.artifactoryServerId", "0")
                                            .put("artifactory.generic.resolvePattern", "")
                                            .put("bintray.vcsUrl", "")
                                            .put("builder.artifactoryGenericBuilder.deployableRepo", "libs-snapshot-local")
                                            .put("bintray.gpgPassphrase", "/* SENSITIVE INFORMATION */")
                                            .build()))))
            .planRepositories(new GitHubRepository()
                    .name("springmvc")
                    .oid(new BambooOid("g5k0yq4pc35t"))
                    .repositoryViewer(new GitHubRepositoryViewer())
                    .repository("miksonx/springmvc")
                    .branch("master")
                    .authentication(new UserPasswordAuthentication("miksonx")
                            .password("xxxx"))
                    .changeDetection(new VcsChangeDetection()))
            
            .planBranchManagement(new PlanBranchManagement()
                    .createForVcsBranch()
                    .delete(new BranchCleanup()
                        .whenRemovedFromRepositoryAfterDays(7)
                        .whenInactiveInRepositoryAfterDays(30))
                    .notificationForCommitters()
                    .issueLinkingEnabled(false));
        return plan;
    }
    
    public PlanPermissions planPermission() {
        final PlanPermissions planPermission = new PlanPermissions(new PlanIdentifier("SPR", "SM"))
            .permissions(new Permissions()
                    .userPermissions("mikson", PermissionType.EDIT, PermissionType.VIEW, PermissionType.ADMIN, PermissionType.CLONE, PermissionType.BUILD));
        return planPermission;
    }
    
    public static void main(String... argv) {
        //By default credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer("http://127.0.1.1:8085");
        final PlanSpec planSpec = new PlanSpec();
        
        final Plan plan = planSpec.plan();
        bambooServer.publish(plan);
        
        final PlanPermissions planPermission = planSpec.planPermission();
        bambooServer.publish(planPermission);
    }
}