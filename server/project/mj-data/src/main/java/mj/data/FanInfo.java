package mj.data;

/**
 * @author zuoge85@gmail.com on 2017/1/19.
 */
public class FanInfo {
    private String name;
    private int score;

    public FanInfo() {
    }

    public FanInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "FanInfo{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
