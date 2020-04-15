# Museum app
## Example
We have the following visiting hours:
* ❌ 09:40 - 10:10
* 🔵 10:00 - 10:30
* 🟪 10:10 - 10:40
* 🔺 10:20 - 10:50

As a result, we get the following time points (TimePoint.class) on the graph:
* 9:40, 10:00, 10:10, 10:20, 10:30, 10:40, 10:50

The following graph shows the intersection of time points and visits.
As we can see, at 10:10, 10:20 and 10:30 the number of visits was maximum (max = 3).

![Plot](/example.png)

Answer: 10:10-10:30;3

## Classes and methods
* **Visit:** Visit. Contains information about the beginning and end of the visit.
  - **getStart():** returns start of the visit
  - **getEnd():** returns end of the visit
* **TimePoint:** Time point. Start or end of a visit plotted on a graph.
  - **getTime():** returns time
  - **getNumberOfVisitors():** returns the number of visitors at that time
  - **incrementNumberOfVisitors():** increments the number of visitors by 1 at that time
* **FileReader:** File reader.
  - **getLines():** reads a file and returns all lines
* **VisitorSearch:** The class describes methods that search the number of visits at a specific time and the most visited time interval.
  - **getVisitsFromFileLines():** converts lines from file to Visit objects
  - **getTimePoints():** converts the start and end of a visit to TimePoints for a graph
  - **countNumberOfVisitorsForEachTimePoint():** counts the number of visitors at each time point
  - **getTimePointsWithMaxNumberOfVisitors():** returns a list of points with the maximum number of visits. Time points are sorted in ascending order
  - **getTimeIntervalWithMaxNumberOfVisitors():** converts a list of time points to a string. Selects the first and last point
