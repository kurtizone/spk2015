clc

a=30;
b=-50;
c=-55;
d=3;

x=-10:1:53
y=a+b*x+c*x.^2+d*x.^3;
plot(x,y)
grid on

minX=fminbnd(@(x) a+b*x+c*x^2+d*x^3,0,53)
minY=a+b*minX+c*minX^2+d*minX^3

x=-10:1:10
y=a+b*x+c*x.^2+d*x.^3;
figure
plot(x,y)
grid on

maxX=fminbnd(@(x) -(a+b*x+c*x^2+d*x^3),-10,10)
maxY=a+b*maxX+c*maxX^2+d*maxX^3