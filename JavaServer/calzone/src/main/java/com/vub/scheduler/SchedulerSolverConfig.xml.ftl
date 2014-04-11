<?xml version="1.0" encoding="UTF-8"?>
<solver>
	<!-- Define the environment -->
	<!-- NOTE: full_assert mode is set for error detection, when app goes in 
		production, use PRODUCTION as value -->
	<environmentMode>PRODUCTION</environmentMode>

	<!-- Define the model -->
	<solutionClass>com.vub.scheduler.Schedular</solutionClass>
	<planningEntityClass>com.vub.model.Entry</planningEntityClass>

	<!-- Define the score function -->
	<scoreDirectorFactory>
		<scoreDefinitionType>HARD_SOFT_LONG</scoreDefinitionType>
		<scoreDrl>/com/vub/scheduler/SchedularScoreRules.drl</scoreDrl>
	</scoreDirectorFactory>

	<!-- Configure the optimization algorithm(s) -->
	<termination>
		<terminationCompositionStyle>OR</terminationCompositionStyle>
		<scoreAttained>0hard/0soft</scoreAttained>
		<!-- Comment maximumSecondsSpend for Benchmarker -->
		<maximumSecondsSpend>10</maximumSecondsSpend>
	</termination>

	<constructionHeuristic>
		<constructionHeuristicType>FIRST_FIT_DECREASING</constructionHeuristicType>
		<forager>
			<pickEarlyType>FIRST_NON_DETERIORATING_SCORE</pickEarlyType>
		</forager>
	</constructionHeuristic>

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
		<!-- changeMoveSelector is the generic form of RowChangeMoveFactory: -->
		<!--<moveListFactory> -->
		<!--<moveListFactoryClass>org.optaplanner.examples.nqueens.solver.move.factory.RowChangeMoveFactory</moveListFactoryClass> -->
		<!--</moveListFactory> -->
		<acceptor>
			<entityTabuSize>10</entityTabuSize>
		</acceptor>
		<forager>
			<!-- An acceptedCountLimit integer, which specifies how many accepted 
				moves should be evaluated during each step. By default, all accepted moves 
				are evaluated at every step. -->
			<acceptedCountLimit>1000</acceptedCountLimit>
		</forager>
		<termination>
			<maximumUnimprovedStepCount>100</maximumUnimprovedStepCount>
		</termination>
	</localSearch>
</solver>