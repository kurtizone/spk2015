x0=0.5;
y0=0.5;
R=0.5;
fi=0;
i=1;
while i<=100
    x(i)=x0+R*cosd(fi);
    y(i)=y0+R*sind(fi);
    fi=fi+3.6;
    i=i+1;
end
x
y
xx=x;
yy=y;
