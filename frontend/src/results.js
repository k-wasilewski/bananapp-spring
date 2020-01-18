import React from 'react';

class Results extends React.Component {

    render() {
        console.log("prediction at front: "+this.props.location.state.prediction);
        return (
            <div>
                <h3> results: </h3>
                <h3> { this.props.location.state.prediction } </h3>
            </div>
        );
    }
}
export default Results;