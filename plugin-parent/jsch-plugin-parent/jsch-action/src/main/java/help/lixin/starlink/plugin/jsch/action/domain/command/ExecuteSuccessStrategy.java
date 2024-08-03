package help.lixin.starlink.plugin.jsch.action.domain.command;

/**
 * 执行成功策略.
 */
public enum ExecuteSuccessStrategy {
    /**
     * 所有的都执行成功, 才会成功.
     */
    ALL_SUCCESS,
    /**
     * 只要有一个执行成功,则成功.
     */
    LEAST_ONCE
}
