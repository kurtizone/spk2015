x=-5.12:0.001:5.12;
z=fun1(x);
plot(x,z)
x= fminsearch(@fun1,-100)


