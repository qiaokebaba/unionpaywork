package TDD.MarsRover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarsCar {

    private AreaInfo areaInfo;
    private Point point;
    private Direction direction;
    private static Map<Object,Object> resultinfo=new HashMap<>();
    private char command;

    public MarsCar(AreaInfo areaInfo, Point point, Direction direction) throws Exception {
        this.areaInfo = areaInfo;
        this.point = point;
        this.direction = direction;
        if(point.getX()<0||point.getY()<0||point.getX()>=areaInfo.getLength()
                ||point.getY()>=areaInfo.getWidth()){
            throw new Exception("火星车的降落地点不在指定区域内！");
        }
    }
    public int getX() {
        return point.getX();
    }
    public int getY() {
        return point.getY();
    }
    public int getLength() {
        return areaInfo.getLength();
    }
    public String getCurDirection() {
        return direction.getCurDirection();
    }
    public int getWidth() {
        return areaInfo.getWidth();
    }
    public AreaInfo getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfo areaInfo) {
        this.areaInfo = areaInfo;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Map<Object,Object> execCommand(MarsCommand command) throws Exception {
        Map<Object,Object> result=new HashMap<>();
        String strcommand=command.getCommand();
        for(int i=0;i<strcommand.length();i++){
          result=MoveOrTurnCommand(this,strcommand.charAt(i));
        }
        return result;
    }


    private Map<Object, Object> MoveOrTurnCommand(MarsCar marsCar, char command) throws Exception {
        System.out.println("cur direction: "+marsCar.getCurDirection());
        System.out.println("cur positon: ("+ marsCar.getPoint().getX()+", "+ marsCar.getPoint().getY()+")");
        switch (command){
            case 'l':
                marsCar =new TurnLeft().turn(marsCar);
                break;
            case 'r':
                marsCar=new TurnRight().turn(marsCar);
                break;
            case 'f':
                marsCar=new MoveForward().move(marsCar);
                break;
            case 'b':
                marsCar=new MoveBack().move(marsCar);
                break;
            default:
                break;
        }
        System.out.println("after positon: ("+ marsCar.getPoint().getX()+", "+ marsCar.getPoint().getY()+")");
        System.out.println("after direction: "+marsCar.getDirection().getCurDirection());
        System.out.println();
        resultinfo.put("Direction",marsCar.getDirection().getCurDirection());
        resultinfo.put("curx",marsCar.getPoint().getX());
        resultinfo.put("cury",marsCar.getPoint().getY());
        return resultinfo;

    }
}
