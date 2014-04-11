package com.vub.scheduler;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.benchmark.config.XmlPlannerBenchmarkFactory;

public class SchedulerBenchmarker {
     public static void main(String [ ] args){
    	 PlannerBenchmarkFactory plannerBenchmarkFactory = new XmlPlannerBenchmarkFactory(
                 "/com/vub/scheduler/SchedulerBenchmarkConfig.xml");

         PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
         plannerBenchmark.benchmark();
     }
}
