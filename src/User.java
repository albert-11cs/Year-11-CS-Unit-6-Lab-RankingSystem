import java.util.Arrays;
import java.util.List;

public class User {
    private int rank;
    private int progress;
    private static final List<Integer> VALID_RANKS = Arrays.asList(-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8);

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    public void incProgress(int rankOfActivity) {
        if (!VALID_RANKS.contains(rankOfActivity)) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        int d = getRankDifference(rankOfActivity);
        int earnedProgress = calculateProgress(d);
        progress += earnedProgress;

        while (progress >= 100 && rank < 8) {
            progress -= 100;
            rank = nextRank(rank);
        }

        if (rank == 8) {
            progress = 0;
        }
    }

    private int getRankDifference(int rankOfActivity) {
        int userIndex = VALID_RANKS.indexOf(rank);
        int activityIndex = VALID_RANKS.indexOf(rankOfActivity);
        return activityIndex - userIndex;
    }

    private int calculateProgress(int d) {
        if (d == 0) {
            return 3;
        } else if (d == -1) {
            return 1;
        } else if (d < -1) {
            return 0;
        }
        return 10 * d * d;
    }

    private int nextRank(int currentRank) {
        int currentIndex = VALID_RANKS.indexOf(currentRank);
        return VALID_RANKS.get(currentIndex + 1);
    }

    @Override
    public String toString() {
        return "User{" + "rank=" + rank + ", progress=" + progress + '}';
    }
}
