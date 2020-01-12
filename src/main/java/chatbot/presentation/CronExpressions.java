package chatbot.presentation;

public enum CronExpressions {
    EVERY_5_SECONDS("0/5 * * * * *"),
    MON_FRI_09_30("0 30 9 ? * MON-FRI"),
    MON_FRI_18_00("0 00 18 ? * MON-FRI");

    private String expression;

    CronExpressions(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
