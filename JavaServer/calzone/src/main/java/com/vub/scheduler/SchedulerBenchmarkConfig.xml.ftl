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
			<xstreamAnnotatedClass>com.vub.scheduler.Schedular
			</xstreamAnnotatedClass>
			<inputSolutionFile>data/schedule_set1.xml</inputSolutionFile>
			<inputSolutionFile>data/schedule_set2.xml</inputSolutionFile>
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
							<variableName>startDate</variableName>
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

	<#list [FIRST_FIT_DECREASING, BEST_FIT_DECREASING] as
	constructionHeuristicType>
	<#list [5, 7, 11, 13] as entityTabuSize>
	<#list [500, 1000, 2000] as acceptedCountLimit>
	<solverBenchmark>
		<name>FFD TABU size: ${entityTabuSize}; acceptedCount:
			${acceptedCountLimit}
		</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}
				</constructionHeuristicType>
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

	<#list [FIRST_FIT_DECREASING, BEST_FIT_DECREASING] as constructionHeuristicType>
	<#list [1hard/1soft, 2hard/2soft] as simulatedAnnealingStartingTemperature>
	<#list [1, 2, 3, 4] as acceptedCountLimit>
	<solverBenchmark>
		<name> ${constructionHeuristicType} Simulated Annealing size:
			${entityTabuSize}; acceptedCount:
			${acceptedCountLimit}
		</name>
		<solver>
			<localSearch>
				<constructionHeuristic>
					<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
				</constructionHeuristic>
				<acceptor>
					<simulatedAnnealingStartingTemperature>${simulatedAnnealingStartingTemperature}</simulatedAnnealingStartingTemperature>
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

	<#list [FIRST_FIT_DECREASING, BEST_FIT_DECREASING] as constructionHeuristicType>
	<#list [400, 500, 600] as lateAcceptanceSize>
	<#list [1, 2, 3, 4] as acceptedCountLimit>
	<solverBenchmark>
		<name>${constructionHeuristicType} Late Acceptance size: ${lateAcceptanceSize}; acceptedCount: ${acceptedCountLimit}</name>
		<solver>
			<localSearch>
				<constructionHeuristic>
					<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
				</constructionHeuristic>
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