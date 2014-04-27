<?xml version="1.0" encoding="UTF-8"?>
<plannerBenchmark>
	<benchmarkDirectory>benchmark</benchmarkDirectory>
	<parallelBenchmarkCount>AUTO</parallelBenchmarkCount>
	<!-- Use this if your datasets differ greatly in size or difficulty, producing 
		a difference in Score magnitude. -->
	<solverBenchmarkRankingType>TOTAL_RANKING</solverBenchmarkRankingType>
	<warmUpSecondsSpend>30</warmUpSecondsSpend>
	<!-- <writeOutputSolutionEnabled>true</writeOutputSolutionEnabled> -->

	<inheritedSolverBenchmark>
		<problemBenchmarks>
			<xstreamAnnotatedClass>com.vub.scheduler.Schedular</xstreamAnnotatedClass>
			<inputSolutionFile>src/main/java/com/vub/scheduler/schedule-1.xml</inputSolutionFile>
			<!-- <inputSolutionFile>/com/vub/scheduler/schedule-2.xml</inputSolutionFile> -->
			<!-- <problemStatisticType>BEST_SCORE</problemStatisticType> -->
		</problemBenchmarks>
		<solver>
			<solutionClass>com.vub.scheduler.Schedular</solutionClass>
			<planningEntityClass>com.vub.model.Entry</planningEntityClass>
			<scoreDirectorFactory>
				<scoreDefinitionType>HARD_SOFT_LONG</scoreDefinitionType>
				<scoreDrl>/com/vub/scheduler/SchedularScoreRules.drl</scoreDrl>
			</scoreDirectorFactory>
			<termination>
				<scoreAttained>0hard/0soft</scoreAttained>
			</termination>
			<localSearch>
				<unionMoveSelector>
					<cacheType>JUST_IN_TIME</cacheType> <!-- Default Value -->
					<selectionOrder>RANDOM</selectionOrder> <!-- Default Value -->
					<changeMoveSelector>
						<valueSelector>
							<variableName>startingDate</variableName>
						</valueSelector>
					</changeMoveSelector>
					<changeMoveSelector>
						<valueSelector>
							<variableName>room</variableName>
						</valueSelector>
					</changeMoveSelector>
				</unionMoveSelector>
				<termination>
					<maximumUnimprovedStepCount>100</maximumUnimprovedStepCount>
				</termination>
			</localSearch>
		</solver>
	</inheritedSolverBenchmark>

	<#list ["FIRST_FIT_DECREASING", "BEST_FIT_DECREASING"] as constructionHeuristicType>
	<#list [5, 7, 11, 13] as entityTabuSize>
	<#list [500, 1000, 2000] as acceptedCountLimit>
	<solverBenchmark>
		<name>${constructionHeuristicType}_TABU_size_${entityTabuSize}_acceptedCount_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
			<localSearch>
				<acceptor>
					<entityTabuSize>${entityTabuSize}</entityTabuSize>
				</acceptor>
				<forager>
					<acceptedCountLimit>${acceptedCountLimit}</acceptedCountLimit>
				</forager>
			</localSearch>
		</solver>
	</solverBenchmark>
	</#list>
	</#list>
	</#list>

	<#list ["FIRST_FIT_DECREASING","BEST_FIT_DECREASING"] as constructionHeuristicType>
	<#list [1, 2, 3, 4] as acceptedCountLimit>
	<solverBenchmark>
		<name> ${constructionHeuristicType}_SA_acceptedCount_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
			<localSearch>
				<acceptor>
					<simulatedAnnealingStartingTemperature>1hard/1soft</simulatedAnnealingStartingTemperature>
				</acceptor>
				<forager>
					<acceptedCountLimit>${acceptedCountLimit}</acceptedCountLimit>
				</forager>
			</localSearch>
		</solver>
	</solverBenchmark>
	</#list>
	</#list>

	<#list ["FIRST_FIT_DECREASING", "BEST_FIT_DECREASING"] as constructionHeuristicType>
	<#list [400, 500, 600] as lateAcceptanceSize>
	<#list [1, 2, 3, 4] as acceptedCountLimit>
	<solverBenchmark>
		<name>${constructionHeuristicType}_LA_size_${lateAcceptanceSize}_acceptedCount_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
			<localSearch>
				<acceptor>
					<lateAcceptanceSize>${lateAcceptanceSize}</lateAcceptanceSize>
				</acceptor>
				<forager>
					<acceptedCountLimit>${acceptedCountLimit}</acceptedCountLimit>
				</forager>
			</localSearch>
		</solver>
	</solverBenchmark>
	</#list>
	</#list>
	</#list>
</plannerBenchmark>