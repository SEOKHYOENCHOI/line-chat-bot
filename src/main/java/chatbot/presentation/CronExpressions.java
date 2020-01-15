package chatbot.presentation;

public enum CronExpressions {
    EVERY_5_SECONDS("0/5 * * * * *"),
    TUE_FRI_09_25("0 25 9 ? * TUE-FRI"),
    MON_12_55("0 55 12 ? * MON"),
    MON_FRI_17_59("0 59 17 ? * MON-FRI"),
    SUN_20_40("0 40 20 ? * SUN");

    private String expression;

    CronExpressions(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
