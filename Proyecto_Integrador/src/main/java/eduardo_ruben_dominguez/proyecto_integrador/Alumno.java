package eduardo_ruben_dominguez.proyecto_integrador;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Alumno {
int legajo;
String nombre;
String materias_aprobadas;
    
public int getlegajo() {
        return legajo;
    }

    public void setlegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterias_aprobadas() {
        return materias_aprobadas;
    }

    public void setMaterias_aprobadas(String materias_aprobadas) {
        this.materias_aprobadas = materias_aprobadas;
    }
 

    public void agregarAlumno(JTextField paraNombre,JTextField paraMateria_Aprobada){

        setNombre(paraNombre.getText());
        setMaterias_aprobadas(paraMateria_Aprobada.getText());

        CConexion objetoConexion = new CConexion();
                
         String consulta = "INSERT INTO alumno(nombre,materias_aprobadas) VALUES(?,?);";
         
         try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setString(1, getNombre());
             cs.setString(2, getMaterias_aprobadas());
             
             cs.execute();
             
             JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
             
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno,error:"+e.toString());
        }
        
}
  
public void VisualizarAlumnos(JTable  paramTablaListaAlumnos){

    CConexion objetoConexion = new CConexion();
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
    paramTablaListaAlumnos.setRowSorter(OrdenarTabla);
    
    String sql;
    
    modelo.addColumn("legajo");
    modelo.addColumn("nombre");
    modelo.addColumn("materias_aprobadas");
    
    paramTablaListaAlumnos.setModel(modelo);
    
    sql = "SELECT * FROM alumno;";
    
    String[] datos = new String[3];
    Statement st;
    
    try {
        st = objetoConexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
        
        datos[0]=rs.getString(1);
        datos[1]=rs.getString(2);
        datos[2]=rs.getString(3);
        
        modelo.addRow(datos);
        
    
    }
        
        paramTablaListaAlumnos.setModel(modelo);
        
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "No se pueden mostrar los registros,error:"+e.toString());
        
    }
    
}

public void SeleccionarAlumno(JTable paramTablaAlumnos,JTextField paramLegajo,JTextField paramNombre,JTextField paramMaterias_Aprobadas){

    try {
        int fila = paramTablaAlumnos.getSelectedRow();
        
        if (fila>=0){
        
            paramLegajo.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
            paramNombre.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
            paramMaterias_Aprobadas.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
            
        }
        else{
        
         JOptionPane.showMessageDialog(null, "Fila no seleccionada");   
        
        }
    } catch (Exception e) {
        
        JOptionPane.showMessageDialog(null, "Error de seleccion,error:"+e.toString());
        
    }


}
  
public void ModificarAlumno(JTextField paramLegajo,JTextField paramNombre,JTextField paramMaterias_Aprobadas){

setlegajo(Integer.parseInt(paramLegajo.getText()));
    setNombre(paramNombre.getText());
    setMaterias_aprobadas(paramMaterias_Aprobadas.getText());

    CConexion objetoConexion = new CConexion();
    
    String consulta ="UPDATE alumno SET alumno.nombre = ?,alumno.materias_aprobadas = ? WHERE alumno.legajo = ?;";

     try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setString(1, getNombre());
             cs.setString(2, getMaterias_aprobadas());
             cs.setInt(3, getlegajo());
             
             cs.execute();
             
             JOptionPane.showMessageDialog(null, "Se modificó correctamente el alumno");
             
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se modificó el alumno,error:"+e.toString());
        }
}
    public void EliminarAlumno(JTextField paramLegajo){
    
        setlegajo(Integer.parseInt(paramLegajo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM alumno WHERE legajo = ?;";
                
        try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             
             cs.setInt(1, getlegajo());
             
             cs.execute();
             
             JOptionPane.showMessageDialog(null, "Se eliminó correctamente el alumno");
             
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el alumno,error:"+e.toString());
        }
        
    }             
        
    }

    
    
    


