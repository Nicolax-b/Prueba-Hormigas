App
│
├── App                ← Capa de presentación (UI/UX)
│   ├── DesktopApp              ← App gráfica (Desktop)
│   │   ├── ViewModels
│   │   │   └── MainViewModel
│   │   └── Views
│   │
│   └── ConsoleApp              ← App de terminal (usuario final)
│       └── SistemaRuso.java    ← Punto de entrada
│
├── BusinessLogic      ← Domain: Lógica de negocio
│   ├── Interfaces              ← Contratos de comportamiento / servicios
│   ├── Services                ← Reglas de negocio
│   └── Entities                ← Domain Models / Entidades del negocio
│
├── DataAccess         ← Persistencia
│   ├── Interfaces              ← Contratos (Repositories / DAOs)
│   ├── DAOs                    ← Implementaciones
│   ├── DTOs                    ← Objetos de transporte
│   └── Helpers                 ← Mappers / utilidades DB
│
└── Infrastructure     ← Preocupaciones transversales
    ├── Config                  ← Configuración
    ├── Logging                 ← Logs
    └── Tools                   ← Utilidades comunes

│
└── Tests                        ← Pruebas unitarias e integración
    ├── DomainTests
    ├── ApplicationTests
    └── InfrastructureTests