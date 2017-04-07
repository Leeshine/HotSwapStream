package hot.swap.proxy.stream;

/**
 * Created by leeshine on 4/6/17.
 */
public enum StatusType {
    // status
    active("active"), inactive("inactive"), rebalancing("rebalancing"), killed("killed"),

    // actions
    activate("activate"), inactivate("inactivate"), monitor("monitor"), startup("startup"), kill("kill"), remove("remove"), rebalance("rebalance"), do_rebalance(
            "do-rebalance"), done_rebalance("done-rebalance"), update_topology("update-topoloogy");

    private String status;

    StatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
