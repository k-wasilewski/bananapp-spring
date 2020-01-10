import React from 'react';
import YouTube from '@u-wave/react-youtube';

class Another extends React.Component {

    render() {
        return (
            <div>
                <YouTube
                    video="fkwxDTNzFoE"
                    autoplay
                />
                <br/>
                <h3>from React link</h3>
            </div>
        );
    }
}
export default Another;