# platform

CountMeUp Exercise

The essence of the implementation may be summarised in terms of the key components:
- VotingService
- Job interface
- Ballot and Candidate model
- BallotBox and Aggregator

The core of the web service is simply the interaction of a user with one of two actions. A voting feature initiates the construction of a VotingJob defined by a unique identifier and the generation of a Ballot object, which comprises the permissible voting intentions of the user. The count action is marked by an attempt to add the underlying Ballot object to the BallotBox cache. Multiple independent voting submissions are gracefully incorporated into the individual Ballot record subject to the three-vote limitation.

Counting of votes is achieved by two mutually exclusive operations: delegation to an Aggregator that sums across the BallotBox collection of Ballots; definitive tallying internal to the BallotBox. Ballot implements a Countable interface, facilitating the Map presentation of count by Candidate name. A number of aggregation strategies were briefly tested, including the use of an Executor to service concurrent grouping of Ballots by Candidate name. The test scenario prescribed the imposition of a voting allocation, which was handled by a concrete BallotAllocator. Sub-one-thousand millisecond durations were recorded with the modified Aggregator tested in isolation with the ten million voting scenario. AggregatorTest served as the substrate for the speculative performance assessment.
