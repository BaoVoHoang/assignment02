package system.user;

public class UserPlan {
    public enum PlanType {
        TRIAL, STANDARD, VIP
    }

    private PlanType planType;
    private boolean isActive;

    public UserPlan(PlanType planType, boolean isActive) {
        this.planType = planType;
        this.isActive = isActive;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "PlanType: " + planType + ", isActive: " + isActive;
    }
}
