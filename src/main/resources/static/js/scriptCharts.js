    let reports = window.reports || [];

    let labels = reports.map(r => {
        const date = new Date(r.workShift.date);
        const day = date.toLocaleDateString('ru-RU', { day: '2-digit', month: '2-digit' });
        const shift = r.workShift.timeOfDay === 'afternoon' ? '08:00' : '20:00'; 
        return `${day}\n ${shift}`;
    });

    let coolantTemp = reports.map(r => r.coolantTemp);
    let dewPoint = reports.map(r => r.dewPoint);
    let gasPollution = reports.map(r => r.gasPollution);
    let oilPressure = reports.map(r => r.oilPressure);
    let vibration = reports.map(r => r.vibration);

    function isLastValueError(data, min, max) {
    const lastValue = data[data.length - 1];
    return lastValue < min || lastValue > max;
    }

    let isError = isLastValueError(coolantTemp, -30, 0) ; 
    new Chart(document.getElementById('coolantTemp'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Температура охлаждающей жикости, С',
                data: coolantTemp,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {
                            maxRotation: 90,  
                            minRotation: 45,  
                            autoSkip: true}
                    },
                y: {min: -40,  max: 10}
            }
        }
    });

    isError = isLastValueError(oilPressure, 4, 6) ; 
    new Chart(document.getElementById('oilPressure'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Давление масла, бар',
                data: oilPressure,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {
                            maxRotation: 90,  
                            minRotation: 45,  
                            autoSkip: true}
                    },
                y: {min: 3.5,  max: 6.5}
            }
        }
    });

    isError = isLastValueError(gasPollution, 0, 10) ; 
    new Chart(document.getElementById('gasPollution'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Загазованность, %',
                data: gasPollution,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {
                            maxRotation: 90,  
                            minRotation: 45,  
                            autoSkip: true}
                    },
                y: {min: 0,  max: 20}
            }
        }
    });


    isError = isLastValueError(dewPoint, -90, -45) ; 
    new Chart(document.getElementById('dewPoint'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Точка росы, С',
                data: dewPoint,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3,
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {
                            maxRotation: 90,  
                            minRotation: 45,  
                            autoSkip: true}
                    },
                y: {min: -90,  max: -35}
            }
        }
    });

    isError = isLastValueError(vibration, 0, 10) ;
    new Chart(document.getElementById('vibration'), {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Вибрация, %',
                data: vibration,
                borderColor: isError ? '#ff0000' : '#42a5f5',
                backgroundColor: isError ? 'rgba(224, 11, 11, 0.1)' : 'rgba(66,165,245,0.1)',
                fill: { target: 'start' },
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            plugins: { legend: { display: true } },
            scales: {
                x: {ticks: {
                            maxRotation: 90,  
                            minRotation: 45,  
                            autoSkip: true}
                    },
                y: {min: 0,  max: 10 }
            }
        }
    });

    