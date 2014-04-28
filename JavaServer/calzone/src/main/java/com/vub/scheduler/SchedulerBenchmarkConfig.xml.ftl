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
			<inputSolutionFile>src/main/java/com/vub/scheduler/schedule-2.xml</inputSolutionFile>
			<!-- <inputSolutionFile>src/main/com/vub/scheduler/schedule-3.xml</inputSolutionFile> -->
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
		</solver>
	</inheritedSolverBenchmark>

	<#list ["FIRST_FIT_DECREASING", "BEST_FIT_DECREASING"] as constructionHeuristicType>
	<#list [5, 7, 11, 13] as entityTabuSize>
	<#list [500, 1000, 2000] as acceptedCountLimit>
	<solverBenchmark>
		<name>${constructionHeuristicType}_TB_siz_${entityTabuSize}_Cnt_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
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
					<terminationCompositionStyle>OR</terminationCompositionStyle>
					<maximumUnimprovedStepCount>100</maximumUnimprovedStepCount>
					<maximumSecondsSpend>20</maximumSecondsSpend>
				</termination>
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
		<name> ${constructionHeuristicType}_SA_Cnt_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
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
					<terminationCompositionStyle>OR</terminationCompositionStyle>
					<maximumUnimprovedStepCount>100</maximumUnimprovedStepCount>
					<maximumSecondsSpend>20</maximumSecondsSpend>
				</termination>
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
		<name>${constructionHeuristicType}_LA_siz_${lateAcceptanceSize}_Cnt_${acceptedCountLimit}</name>
		<solver>
			<constructionHeuristic>
				<constructionHeuristicType>${constructionHeuristicType}</constructionHeuristicType>
			</constructionHeuristic>
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
					<terminationCompositionStyle>OR</terminationCompositionStyle>
					<maximumUnimprovedStepCount>100</maximumUnimprovedStepCount>
					<maximumSecondsSpend>20</maximumSecondsSpend>
				</termination>
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