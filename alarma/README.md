

# 🚀 Proyecto Alarma

Este es un proyecto desarrollado en **Angular**, diseñado para ejecutarse en un entorno local. A continuación, encontrarás las instrucciones para instalar, configurar y ejecutar la aplicación.
# Alarma

## 📋 **Requisitos Previos**

Antes de ejecutar el proyecto, asegúrate de tener instalado:
This project was generated with 
[Angular CLI](https://github.com/angular/angular-cli)  version 15.2.6.

- [Node.js](https://nodejs.org/) (Versión recomendada: **LTS**)
- [Angular CLI](https://angular.io/cli) (Interfaz de Línea de Comandos de Angular) 
- [Git](https://git-scm.com/) (Opcional, pero recomendado)

Puedes verificar las versiones instaladas ejecutando:

```sh
node -v
npm -v
ng version
```

Si Angular CLI no está instalado, puedes hacerlo con:

```sh
npm install -g @angular/cli
```

---

## 👅 **Instalación del Proyecto**

1️⃣ **Clona el repositorio**:

```sh
git clone https://github.com/nicoGarciaValde/alarma-ux
```

2️⃣ **Accede al directorio del proyecto**:

```sh
cd alarma
```

3️⃣ **Instala las dependencias**:

```sh
npm install
```
## ⚠️ **Solución de Problemas**
- Si tienes errores al instalar dependencias, prueba:
```sh
rm -rf node_modules package-lock.json
npm install
```
---

## ▶️ **Ejecutar el Proyecto en Desarrollo**

Para iniciar la aplicación en **modo desarrollo**, usa:

```sh
ng serve --open
```

Esto iniciará un servidor local en:

```sh
http://localhost:4200/
```

Si quieres abrirlo automáticamente en el navegador:

```sh
ng serve --open
```

---

## 💁️ **Estructura del Proyecto**

```md
repo-angular/
│── src/                  # Código fuente del proyecto
│   ├── app/              # Componentes y servicios principales
│   ├──├── feature/       # Componentes desarrollados  
│   ├──├── shared/        # COmponentes compartidos

│   ├── assets/           # Recursos estáticos (imágenes, estilos, etc.)
│   ├── environments/     # Configuración de entornos
│   ├── main.ts           # Punto de entrada de la aplicación
│   └── index.html        # Archivo HTML principal
│
├── angular.json          # Configuración de Angular
├── package.json          # Dependencias del proyecto
├── README.md             # Documentación del proyecto
├── tsconfig.json         # Configuración de TypeScript
└── .gitignore            # Archivos a ignorar en Git
```

---

## 📏 **Notas Adicionales**
- Puedes cambiar el puerto por defecto con:

  ```sh
  ng serve --port=4300
  ```

## ⚠️ **Solución de Problemas**
- Si tienes errores al instalar dependencias, prueba:
```sh
rm -rf node_modules package-lock.json
npm install
```
- Si el servidor no responde, asegúrate de que el puerto 4200 no esté ocupado:
```sh
npx kill-port 4200
```
- Si el problema persiste, verifica qué proceso usa ese puerto:
```sh
lsof -i :4200   # Mac/Linux
netstat -ano | findstr :4200  # Windows
```


---

## 📞 **Soporte**
Si tienes problemas al ejecutar el proyecto, contáctanos a:
📧 Correo:  **jc.hernandez23@uniandes.edu.co**, **ne.garciav1@uniandes.edu.co**, **nico.ing.mec.2@gmail.com**  
💬 También estamos disponibles en **Slack**.
