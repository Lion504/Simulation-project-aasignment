**Call Centre Simulation: Clock 16 (Event B1)**


| Phase | Router Queue | Router | Oper 1 queue | Oper 1 | Oper 2 queue | Oper 2 |
| ----- | ------------ | ------ | ------------ | ------ | ------------ | ------ |
| B     | Empty        | Idle   | X3           | Idle   | Empty        | Y1     |
| C     | Empty        | X3     | Empty        | X3     | Empty        | Y1     |

Event List: B4 finished then give number X = 2


| Event     | Time |
| --------- | ---- |
| B4        | 20   |
| B5        | 18   |
| B1-Next X | 20   |
| B2-Next Y | 20   |

Results:

| Work


| complete |   |
| -------- | - |
| X        | 2 |
| Y        | 0 |

**Call Centre Simulation: Clock 18 (Event B1)**


| Phase | Router Queue | Router | Oper 1 queue | Oper 1 | Oper 2 queue | Oper 2 |
| ----- | ------------ | ------ | ------------ | ------ | ------------ | ------ |
| B     | Empty        | Idle   | Empty        | X3     | Empty        | Idle   |
| C     | Empty        | Idle   | Empty        | X3     | Empty        | Idle   |

Event List: B4 finished then give number Y = 1


| Event                   | Time |
| ----------------------- | ---- |
|                         |      |
|                         |      |
| B4-O1-X3 starting at 16 | 20   |
| B1-Next X               | 20   |
| B2-Next Y               | 20   |

Results:

| Work


| complete |   |
| -------- | - |
| X        | 2 |
| Y        | 1 |
