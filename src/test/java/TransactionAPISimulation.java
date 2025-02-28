import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class TransactionAPISimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("*/*") // 修改为与 curl 一致的 Accept 头
            .contentTypeHeader("application/json")
            .header("User-Agent", "curl/8.7.1"); // 添加与 curl 一致的 User-Agent 头

    private ScenarioBuilder createTransactionScenario = scenario("Create Transaction Scenario")
            .exec(
                    http("Create Transaction")
                            .post("/api/transactions")
                            .body(StringBody("""
                                    {"id": "transaction_1", "type": "DEPOSIT", "amount": 100.0}
                                    """))
                            .check(status().is(201))
            );

    private ScenarioBuilder listTransactionsScenario = scenario("List Transactions Scenario")
            .exec(
                    http("List Transactions")
                            .get("/api/transactions")
                            .check(status().is(200))
            );

    {
        setUp(
                createTransactionScenario.injectOpen(
                        atOnceUsers(10)
                ),
                listTransactionsScenario.injectOpen(
                        atOnceUsers(10)
                )
        ).protocols(httpProtocol);
    }
}