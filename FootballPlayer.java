public record FootballPlayer(String name, String position) implements Player {
    @Override
        public String getName(){
        return name;
    }
    @Override
    public String getPosition(){
        return position;
    }
}
