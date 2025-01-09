public class Main {
    public static void main(String[] args) {
        //Basketball
        BasketballTeam gamers = new BasketballTeam("Gamers");
        BasketballTeam loosers = new BasketballTeam("Loosers");

        scoreResults(gamers,3,loosers,2);
        var jonas = new BasketBallPlayer("Jonas","centras");
        var karolis = new BasketBallPlayer("Karolis", "other");
        gamers.addTeamMember(jonas);
        gamers.addTeamMember(karolis);
        gamers.listMembers();
        //Football
        SportTeam teamOne = new SportTeam("Footbal VIP");
        SportTeam teamTwo = new SportTeam("Football Turin");

        scoreResults(teamOne,3,teamTwo,2);
        var ignas = new FootballPlayer("Jonas","centras");
        var andrius = new FootballPlayer("Karolis", "other");
        teamOne.addTeamMember(ignas);
        teamTwo.addTeamMember(andrius);
        teamOne.listMembers();
        //Team
        Team<BasketBallPlayer> superTeamOne = new Team<>("BasketBall VIP");
        Team<BasketBallPlayer> superTeamTwo = new Team<>("Basketball Turin");

        scoreResults(superTeamOne,3,superTeamTwo,2);
        var darius = new BasketBallPlayer("Darius","centras");
        var simas = new BasketBallPlayer("Simas", "other");
        superTeamOne.addTeamMember(darius);
        superTeamTwo.addTeamMember(simas);
        superTeamOne.listMembers();

    }

    public static void scoreResults(BasketballTeam team1, int t1_score, BasketballTeam team2, int t2_score){
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t2_score);
        System.out.printf("%s,%s,%s,%n ", team1, message, team2);
    }
    public static void scoreResults(SportTeam team1, int t1_score, SportTeam team2, int t2_score){
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t2_score);
        System.out.printf("%s,%s,%s,%n ", team1, message, team2);
    }
    public static void scoreResults(Team team1, int t1_score, Team team2, int t2_score){
        String message = team1.setScore(t1_score,t2_score);
        team2.setScore(t2_score,t2_score);
        System.out.printf("%s,%s,%s,%n ", team1, message, team2);
    }
}