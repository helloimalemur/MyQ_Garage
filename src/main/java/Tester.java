public class Tester {
    public static void main(String[] args) throws Exception {
        String example = "Example string";
        ToJSON tj = new ToJSON();
        tj.setContent(example);
        tj.setUsername("Tom");
        System.out.println(tj.getJSON());
    }
}
