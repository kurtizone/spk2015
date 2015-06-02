 [X1, X2] = meshgrid(-500:.5:50); 
             Z =418.9829 -X1.*sin(sqrt(abs(X1)))-X2.*sin(sqrt(abs(X2)));
             mesh(Z)
x = fminsearch(@fun2,[0,50])
y = fun2(x)