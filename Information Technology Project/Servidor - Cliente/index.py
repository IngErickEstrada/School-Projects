from flask import Flask, render_template
import serial

arduino = serial.Serial('/dev/ttyACM0', 9600)
app = Flask(__name__)

# Pagina de Inicio
@app.route('/')
def home():
    return render_template('home.html')

# Diagrama de Casa
@app.route('/diagrama')
def diagrama():
    return render_template('diagrama.html')

# Recamara Principal
@app.route('/recamara_principal')
def recamara_principal():
    return render_template('recamara_principal.html')

@app.route('/encender_rp')
def encender_rp():
    arduino.write('P'.encode())
    return render_template('encender_rp.html')
    
@app.route('/apagar_rp')
def apagar_rp():
    arduino.write('Q'.encode())
    return render_template('apagar_rp.html')

# Recamara Secundaria
@app.route('/recamara_secundaria')
def recamara_secundaria():
    return render_template('recamara_secundaria.html')

@app.route('/encender_rs')
def encender_rs():
    arduino.write('S'.encode())
    return render_template('encender_rs.html')
    
@app.route('/apagar_rs')
def apagar_rs():
    arduino.write('T'.encode())
    return render_template('apagar_rs.html')

# Baño
@app.route('/bano')
def bano():
    return render_template('bano.html')

@app.route('/encender_ba')
def encender_ba():
    arduino.write('B'.encode())
    return render_template('encender_ba.html')
    
@app.route('/apagar_ba')
def apagar_ba():
    arduino.write('A'.encode())
    return render_template('apagar_ba.html')

# Cocina
@app.route('/cocina')
def cocina():
    return render_template('cocina.html')

@app.route('/encender_cn')
def encender_cn():
    arduino.write('C'.encode())
    return render_template('encender_cn.html')
    
@app.route('/apagar_cn')
def apagar_cn():
    arduino.write('D'.encode())
    return render_template('apagar_cn.html')

# Comedor
@app.route('/comedor')
def comedor():
    return render_template('comedor.html')

@app.route('/encender_cm')
def encender_cm():
    arduino.write('M'.encode())
    return render_template('encender_cm.html')
    
@app.route('/apagar_cm')
def apagar_cm():
    arduino.write('N'.encode())
    return render_template('apagar_cm.html')

# Sala
@app.route('/sala')
def sala():
    return render_template('sala.html')

@app.route('/encender_sl')
def encender_sl():
    arduino.write('E'.encode())
    return render_template('encender_sl.html')
    
@app.route('/apagar_sl')
def apagar_sl():
    arduino.write('F'.encode())
    return render_template('apagar_sl.html')

# Ventilador
@app.route('/encender_vn1')
def encender_vn1():
    arduino.write('V'.encode())
    return render_template('encender_vn1.html')

@app.route('/velocidad1')
def velocidad1():
    arduino.write('X'.encode())
    return render_template('velocidad1.html')

@app.route('/velocidad2')
def velocidad2():
    arduino.write('Y'.encode())
    return render_template('velocidad2.html')

@app.route('/velocidad3')
def velocidad3():
    arduino.write('Z'.encode())
    return render_template('velocidad3.html')

@app.route('/apagar_vn1')
def apagar_vn1():
    arduino.write('U'.encode())
    return render_template('apagar_vn1.html')

# Cochera
@app.route('/cochera')
def cochera():
    return render_template('cochera.html')

@app.route('/encender_ch')
def encender_ch():
    arduino.write('J'.encode())
    return render_template('encender_ch.html')
    
@app.route('/apagar_ch')
def apagar_ch():
    arduino.write('K'.encode())
    return render_template('apagar_ch.html')

# Alarma
@app.route('/encender_alarma')
def encender_alarma():
    arduino.write('H'.encode())
    return render_template('encender_alarma.html')
    
@app.route('/apagar_alarma')
def apagar_alarma():
    arduino.write('L'.encode())
    return render_template('apagar_alarma.html')

if __name__ == '__main__':
    app.run()
       
arduino.close()
