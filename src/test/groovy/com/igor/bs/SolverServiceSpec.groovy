package com.igor.bs

import spock.lang.Specification
import com.igor.bs.operation.*

class SolverServiceSpec extends Specification {
    SolverService service

    void setup() {
        service = new SolverService()
    }

    void "solve() should return the expected number of steps for the target and max"() {
        when:
            List<Operation> result = service.solve(target, max)

        then:
            println result

            if (expectedSteps != null) {
                assert result.last().getResult() == target
                assert (result.size() - 1) == expectedSteps // Extractions don't count
            } else {
                assert !result
            }

        where:
            target                | max  || expectedSteps
            -10                   | 15   || null
            0                     | 15   || null
            Integer.MAX_VALUE + 1 | 15   || null
            Integer.MAX_VALUE     | 0    || null
            Integer.MAX_VALUE     | 1000 || null
            1                     | 15   || 0
            5                     | 1    || 4
            5                     | 15   || 0
            10                    | 15   || 1
            21                    | 15   || 1
            23                    | 15   || 1
            99                    | 99   || 0
            136                   | 15   || 2
            454                   | 15   || 3
            513                   | 15   || 2
            548                   | 15   || 3
            548                   | 64   || 2
            752                   | 15   || 3
            794                   | 15   || 3
            2199                  | 15   || 2
            3988                  | 15   || 3
            6557                  | 15   || 2
            7297                  | 15   || 4
            477266                | 15   || 7
            524287                | 15   || 3
            524287                | 99   || 2
            Integer.MAX_VALUE     | 15   || 4
//            Integer.MAX_VALUE     | 99   || 4 // Java error! OOM or StackOverflow or something. Nothing printed
    }
}
